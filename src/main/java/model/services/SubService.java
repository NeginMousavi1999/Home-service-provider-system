package model.services;

import lombok.*;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Service service;
    private double cost;
    private String description;
}
