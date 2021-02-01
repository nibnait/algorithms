package common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

/**
 * Created by nibnait on 2021/01/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailBO {

    private String fromNickName;

    private String toAddress;

    private String ccAddress;

    private String subject;

    private String content;

    private List<File> attachFiles;
}
