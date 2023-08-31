package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tag",
        "status"
})
public class PetDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("category")
    private CategoryDTO categoryDTO;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private String[] photoUrls;
    @JsonProperty("tags")
    private TagDTO[] tagDTO;
    @JsonProperty("status")
    private Enum status;

}
