package ir.maktab.service;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.entity.members.Expert;

/**
 * @author Negin Mousavi
 */
public interface ExpertService {
    void save(ExpertDto expert);

    boolean delete(ExpertDto expert);

    boolean update(ExpertDto expert);

    ExpertDto findByEmail(String email);

    Long getCountOfRecords();
}
