package com.insys.trapps.model;

import lombok.*;

//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Getter
    @Setter
    @NonNull
    private String name;
}
