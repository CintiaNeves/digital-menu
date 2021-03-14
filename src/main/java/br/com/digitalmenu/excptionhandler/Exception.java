package br.com.digitalmenu.excptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)
public class Exception {

    private Integer status;
    private OffsetDateTime time;
    private String title;
    private List<Field> fields;

    @Data
    public static class Field {
        private String name;
        private String msg;

        public Field(String name, String msg) {
            super();
            this.name = name;
            this.msg = msg;
        }
    }
}
