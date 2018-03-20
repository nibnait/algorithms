package zzzTest;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       String url = "https://fuss.alpha.elenet.me:9090/3/04/5a256243be53173844ceebdf1ea16jpeg.jpeg";
        String[] split = url.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < split.length; i++) {
            sb.append(split[i]);
        }
        String str = sb.toString();
        String[] split1 = str.split("/");
        System.out.println(split1[0]);
    }
}

