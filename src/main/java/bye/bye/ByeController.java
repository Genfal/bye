package bye.bye;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

    private static Integer helloCounter = 0;
    private static Integer byeCounter = 0;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ByeDAO byeDAO;

    private final ByeModel byeModel;

    @Autowired
    public ByeController(KafkaTemplate<String, String> kafkaTemplate, ByeDAO byeDAO) {
        this.kafkaTemplate = kafkaTemplate;
        this.byeDAO = byeDAO;
        byeModel = byeDAO.findById(1L).get();
        helloCounter = byeModel.getHelloCounter();
        byeCounter = byeModel.getByeCounter();
    }

    @GetMapping("/bye")
    public String bye() {
        kafkaTemplate.send("bye", (++byeCounter).toString());
        return "Всего доброго! Вы здоровались " + helloCounter + " раз";
    }

    @KafkaListener(topics = "hello")
    public void counter(ConsumerRecord<String, String> record) {
        helloCounter = Integer.parseInt(record.value());
        byeModel.setHelloCounter(helloCounter);
        byeDAO.save(byeModel);
    }

    @KafkaListener(topics = "bye")
    public void bye2(ConsumerRecord<String, String> record) {
        byeCounter = Integer.parseInt(record.value());
        byeModel.setByeCounter(byeCounter);
        byeDAO.save(byeModel);
    }
}
