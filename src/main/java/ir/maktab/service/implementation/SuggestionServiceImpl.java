package ir.maktab.service.implementation;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.SuggestionDto;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.repository.SuggestionRepository;
import ir.maktab.exception.HomeServiceException;
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
        suggestionRepository.save(SuggestionMapper.mapSuggestionDtoToSuggestionForUpdate(suggestionDto));
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
        return suggestionRepository.findAll(Sort.by("suggestedPrice", "expert").descending())
                .stream().map(SuggestionMapper::mapSuggestionToSuggestionDto).collect(Collectors.toList());
    }
}
