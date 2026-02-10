package org.registration.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String number;

    private String citycode;

    private String countrycode;

}
