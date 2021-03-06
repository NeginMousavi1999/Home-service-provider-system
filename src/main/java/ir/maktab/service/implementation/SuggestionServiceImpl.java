package ir.maktab.service.implementation;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.SuggestionDto;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.repository.SuggestionRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.OrderService;
import ir.maktab.service.SuggestionService;
import ir.maktab.util.mapper.ExpertMapper;
import ir.maktab.util.mapper.OrderMapper;
import ir.maktab.util.mapper.SuggestionMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class SuggestionServiceImpl implements SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final OrderService orderService;
    private final int suffix = 1000;

    public void addNewSuggestion(SuggestionDto suggestionDto, OrderDto orderDto) {
        orderService.updateStatus(orderDto);
        saveSuggestion(suggestionDto);
    }

    public void saveSuggestion(SuggestionDto suggestionDto) {
        suggestionRepository.save(SuggestionMapper.mapSuggestionDtoToSuggestionForSaving(suggestionDto));
    }

    public List<SuggestionDto> getByStatus(ExpertDto expertDto, SuggestionStatus suggestionStatus) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findBySuggestionStatusAndExpert(suggestionStatus,
                ExpertMapper.mapExpertDtoToExpert(expertDto));
        if (suggestions.isEmpty())
            throw new HomeServiceException("no suggestion to show!");
        return suggestions.get().stream().map(SuggestionMapper::mapSuggestionToSuggestionDto).collect(Collectors.toList());
    }

    public List<SuggestionDto> getAllSuggestions(ExpertDto expertDto) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findByExpert(ExpertMapper.mapExpertDtoToExpert(expertDto));
        if (suggestions.isEmpty())
            throw new HomeServiceException("you have no suggestion!");
        return suggestions.get().stream().map(SuggestionMapper::mapSuggestionToSuggestionDto).collect(Collectors.toList());
    }

    public void update(SuggestionDto suggestionDto) {
        suggestionRepository.updateStatus(suggestionDto.getIdentity() - suffix, suggestionDto.getSuggestionStatus());
    }

    public Set<SuggestionDto> getByOrder(OrderDto orderDto) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findByOrder(OrderMapper.mapOrderDtoToOrderWithId(orderDto));
        if (suggestions.isEmpty())
            throw new HomeServiceException("nothing to show!!");
        return suggestions.get().stream().map(SuggestionMapper::mapSuggestionToSuggestionDto).collect(Collectors.toSet());
    }

    public Long getCountOfRecords() {
        return suggestionRepository.count();
    }

    @Override
    public List<SuggestionDto> getSortedByOrder(OrderDto orderDto) {
        List<SuggestionDto> suggestionDtoList = suggestionRepository.findAll(Sort.by("suggestedPrice", "expert").descending())
                .stream().map(SuggestionMapper::mapSuggestionToSuggestionDtoForSorting).collect(Collectors.toList());
        return suggestionDtoList.stream().filter(suggestionDto -> suggestionDto.getOrder().getIdentity() == orderDto.getIdentity()).collect(Collectors.toList());
    }

    @Override
    public void chooseSuggestion(int suggestionIdentity, List<SuggestionDto> suggestions) {
        SuggestionDto suggestion = null;
        for (SuggestionDto suggestionDto : suggestions) {
            if (suggestionDto.getIdentity() == suggestionIdentity) {
                suggestion = suggestionDto;
                suggestion.setSuggestionStatus(SuggestionStatus.ACCEPTED);
            } else
                suggestionDto.setSuggestionStatus(SuggestionStatus.REJECTED);
        }
        assert suggestion != null;
        ExpertDto expert = suggestion.getExpert();
        OrderDto order = suggestion.getOrder();
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
            throw new HomeServiceException("something is wrong!");
        order.setExpert(expert);
        order.setFinalPrice(suggestion.getSuggestedPrice());
        order.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
        order.setToBeDoneDate(suggestion.getStartTime());
        orderService.update(order);
        suggestions.forEach(this::update);
    }
}
