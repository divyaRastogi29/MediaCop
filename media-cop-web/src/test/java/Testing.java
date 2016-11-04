import com.follow.me.Request.GetTopHashTagRequest;
import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by divya on 25/10/16.
 */
public class Testing {

    @Test
    public void positiveTest(){
        Gson gson = new Gson();
        String country = "australia";
        GetTopHashTagRequest request = new GetTopHashTagRequest();
        request.setCountry(country);
        System.out.println("\n"+gson.toJson(request));
    }
}
