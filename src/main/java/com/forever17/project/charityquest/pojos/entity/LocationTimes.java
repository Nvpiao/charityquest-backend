package com.forever17.project.charityquest.pojos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * location and times POJO
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 16 Mar 2020
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationTimes {

    /**
     * location
     */
    private String location;

    /**
     * times of location which appeared.
     */
    private long times;
}
