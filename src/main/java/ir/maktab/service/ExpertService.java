package ir.maktab.service;

import ir.maktab.data.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
public interface ExpertService {
    void save(ExpertDto expert);

    boolean delete(ExpertDto expert);

    boolean update(ExpertDto expert);

    ExpertDto findByEmail(String email);

    Long getCountOfRecords();

    void addSubServices(ExpertDto expertDto, SubServiceDto subServiceDto);

    Set<SubServiceDto> getSubServices(ExpertDto expertDto);

    void updateScore(ExpertDto expertDto, double score);

    void addNewSuggestion(String date, double suggestedPrice, int durationOfWork, OrderDto orderDto, ExpertDto expertDto);

    Map<UserDto, Integer> getExpertAndNumberOfRegisteredRequests();

    List<ExpertDto> getAll();

    List<SuggestionDto> getSuggestions(ExpertDto expertDto);
}
