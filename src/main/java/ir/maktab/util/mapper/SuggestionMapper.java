package ir.maktab.util.mapper;

import ir.maktab.data.dto.SuggestionDto;
import ir.maktab.data.entity.order.Suggestion;

/**
 * @author Negin Mousavi
 */
public class SuggestionMapper {
    private static final int suffix = 1000;

    public static Suggestion mapSuggestionDtoToSuggestionForUpdate(SuggestionDto suggestionDto) {
        return Suggestion.builder()

                .build();
    }

    public static Suggestion mapSuggestionDtoToSuggestionForSaving(SuggestionDto suggestionDto) {
        return Suggestion.builder()
                .startTime(suggestionDto.getStartTime())
                .suggestedPrice(suggestionDto.getSuggestedPrice())
                .durationOfWork(suggestionDto.getDurationOfWork())
                .expert(ExpertMapper.mapExpertDtoToExpert(suggestionDto.getExpert()))
                .suggestionStatus(suggestionDto.getSuggestionStatus())
                .order(OrderMapper.mapOrderDtoToOrderForSavingSuggestion(suggestionDto.getOrder()))
                .build();
    }

    public static SuggestionDto mapSuggestionToSuggestionDto(Suggestion suggestion) {
        return SuggestionDto.builder()
                .identity(suggestion.getId() + suffix)
                .startTime(suggestion.getStartTime())
                .suggestedPrice(suggestion.getSuggestedPrice())
                .durationOfWork(suggestion.getDurationOfWork())
                .expert(ExpertMapper.mapExpertToExpertDto(suggestion.getExpert()))
                .registrationDate(suggestion.getRegistrationDate())
                .suggestionStatus(suggestion.getSuggestionStatus())
                .build();
    }
}
