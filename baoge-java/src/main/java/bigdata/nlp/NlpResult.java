package bigdata.nlp;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class NlpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String log_id;
    private Double score;
    private JSONObject texts;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public JSONObject getTexts() {
        return texts;
    }

    public void setTexts(JSONObject texts) {
        this.texts = texts;
    }

    @Override
    public String toString() {
        return "NlpResult{" +
                "log_id='" + log_id + '\'' +
                ", score=" + score +
                ", texts=" + texts +
                '}';
    }
}
