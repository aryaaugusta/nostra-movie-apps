package com.nostra.nostramovieapps.share;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class ApplicationProperties {

    // class untuk parameter yg diperlukan untuk example value di swagger ui
    @Getter
    @Setter
    public static class ApplicationProperties1 {

        @ApiModelProperty(example = "Action", required = true)
        private String search;
    }

    @Getter
    @Setter
    public static class ApplicationProperties2 {

        @ApiModelProperty(example = "Jurassic", required = true)
        private String search;
    }

    @Getter
    @Setter
    public static class ApplicationProperties3 {

        @ApiModelProperty(example = "Jurassic World: Fallen Kingdom", required = true)
        private String title;

        @ApiModelProperty(example = "After Isla Nublar was devastated by a volcanic eruption, the remaining dinosaur species were taken to the huge Lockwood plantation, in America. " +
                "There, Owen and Claire realize that dinosaur species are being auctioned off and not being preserved. A very dangerous hybrid dinosaur known as Indoraptor, " +
                "escapes and begins to terrorize the residents around the plantation.", required = true)
        private String overview;

        @ApiModelProperty(example = "6.2", required = true)
        private String voteAverage;
    }
}
