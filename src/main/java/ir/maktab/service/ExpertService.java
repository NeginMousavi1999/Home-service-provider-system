package ir.maktab.service;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.ServiceDto;

import java.util.List;
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

    void addServices(ExpertDto expertDto, List<ServiceDto> serviceDtos);

    Set<ServiceDto> getServices(ExpertDto expertDto);

    void updateScore(ExpertDto expertDto, double score);

    void addNewSuggestion(String date, double suggestedPrice, int durationOfWork, OrderDto orderDto, ExpertDto expertDto);
}
