package ir.maktab.service.implementation;

import ir.maktab.data.dto.*;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.SuggestionService;
import ir.maktab.util.GenerateDate;
import ir.maktab.util.mapper.ExpertMapper;
import ir.maktab.util.mapper.ServiceMapper;
import ir.maktab.util.mapper.UserMapper;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private final SuggestionService suggestionService;
    private final OrderService orderService;
    private final Validation validation;
    private final ModelMapper modelMapper = new ModelMapper();

    public void save(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        System.out.println(expert);
        expertRepository.save(expert);
    }

    public boolean delete(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.delete(expert);
        return true;
    }

    public boolean update(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.save(expert);
        return true;
    }

    public ExpertDto findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        return ExpertMapper.mapExpertToExpertDto(expert.get());
    }

    public Long getCountOfRecords() {
        return expertRepository.count();
    }

    public void addServices(ExpertDto expertDto, List<ServiceDto> serviceDtos) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        Optional<List<ir.maktab.data.entity.services.Service>> optionalServices = expertRepository
                .customeGetServiceByExpertId(expert.getId());
        List<ir.maktab.data.entity.services.Service> list;
        list = optionalServices.orElseGet(ArrayList::new);
        serviceDtos.stream().map(ServiceMapper::mapServiceDtoToService)
                .filter(service -> !list.contains(service)).forEach(list::add);
        expert.setServices(new HashSet<>(list));
        expertRepository.save(expert);
    }

    @Override
    public Set<ServiceDto> getServices(ExpertDto expertDto) {
        Optional<List<ir.maktab.data.entity.services.Service>> services = expertRepository
                .customeGetServiceByExpertId(ExpertMapper.mapExpertDtoToExpert(expertDto).getId());
        if (services.isEmpty())
            throw new HomeServiceException("no services!");
        return services.get().stream().map(ServiceMapper::mapServiceToServiceDto).collect(Collectors.toSet());
    }

    @Override
    public void updateScore(ExpertDto expertDto, double score) {
        double oldScore = expertDto.getScore();
        expertDto.setScore((oldScore + score) / 2);
        update(expertDto);
    }

    @Override
    public void addNewSuggestion(String date, double suggestedPrice, int durationOfWork, OrderDto orderDto, ExpertDto expertDto) {
        Set<SuggestionDto> orderDtoSuggestions = suggestionService.getByOrder(orderDto);
        assert orderDto != null;
        orderDto.setSuggestions(orderDtoSuggestions);
        validation.validateUserStatus(UserStatus.CONFIRMED, expertDto.getUserStatus());
        orderDto.setSuggestions(orderDtoSuggestions);
        SuggestionDto suggestionDto = SuggestionDto.builder()
                .expert(expertDto)
                .order(orderDto)
                .durationOfWork(durationOfWork)
                .suggestedPrice(suggestedPrice)
                .startTime(GenerateDate.generateByPattern("yyyy-MM-dd", date))
                .suggestionStatus(SuggestionStatus.NEW)
                .build();
        orderDto.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        orderDtoSuggestions.add(suggestionDto);
        orderDto.setSuggestions(orderDtoSuggestions);
        suggestionService.addNewSuggestion(suggestionDto, orderDto);
    }

    @Override
    public Map<UserDto, Integer> getExpertAndNumberOfRegisteredRequests() {
        List<ExpertDto> experts = getAll();
        Map<UserDto, Integer> map = new HashMap<>();
        for (ExpertDto expert : experts) {
            int count = orderService.findNumberOfOrdersPlacedByExpert(expert);
            map.put(UserMapper.mapExpertToUserDto(expert), count);
        }
        return map;
    }

    @Override
    public List<ExpertDto> getAll() {
        List<Expert> experts = (List<Expert>) expertRepository.findAll();
        return experts.stream().map(ExpertMapper::mapExpertToExpertDto).collect(Collectors.toList());
    }
}
