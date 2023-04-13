package com.onda2me.app.util;

import org.springframework.stereotype.Component;

/**
 * 공통변수
 * 
 * @author Administrator
 *
 */
@Component("IConstants")
public class IConstants {
    
    public static final int PAGE_LIST_PER = 10;
    
    public static final int PAGE_MAX_PAGE = 10;
    
    public static final String BATCH_LAUNCH_DATE = "launchDate";

    public static final String BATCH_LAUNCH_TIME = "launchTime";

    public static final String BATCH_CHANNEL_NAME = "channel";

    public static final String BATCH_CHANNEL_WEB = "WEB";

    public static final String BATCH_CHANNEL_QUARTZ = "QUARTZ";

    public static final String BATCH_CHANNEL_SCHEDULER = "SCHEDULER";
  
    public enum Status {
        ACTIVE("01"), INACTIVE("00"); // name(value)

        private final String value;

        private Status(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public enum CategoryCode {
        AL("전체"), EL("전자제품"), NC("생활용품"), CA("자동차"); // name(value)

        private final String value;

        private CategoryCode(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
    
    public enum RoleCode {
        AL("전체"), US("사용자"), CS("운영자"), AD("관리자"); // name(value)
        
        private final String value;

        private RoleCode(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    } 
    
    public enum UseYn {
        
        Y("사용함"), N("사용안함"); // name(value)

        private final String value;

        private UseYn(String value) {
            this.value = value;
        }
        
        public String value() {
            return value;
        }
    }    
}
