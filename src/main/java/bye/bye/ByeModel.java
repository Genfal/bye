package bye.bye;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ByeStatistic")
@Data
public class ByeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private int helloCounter;

    private int byeCounter;
}
