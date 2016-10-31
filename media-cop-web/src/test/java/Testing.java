import com.follow.me.Request.AddHashtagRequest;
import com.follow.me.Request.HashTag;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by divya on 25/10/16.
 */
public class Testing {

    @Test
    public void positiveTest(){
        Gson gson = new Gson();
        List<HashTag> list = new ArrayList<>();
        HashTag a = new HashTag();
        a.setName("#follow");
        a.setPriority(0.5f);
        a.setCountry("India");
        list.add(a);
        HashTag b = new HashTag();
        b.setName("#beautiful");
        b.setPriority(0.78f);
        b.setCountry("Australia");
        list.add(b);
        AddHashtagRequest request = new AddHashtagRequest(list);
        System.out.println("\n\n"+gson.toJson(request));
    }
}
