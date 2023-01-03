package com.mygrammar.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RowResponse {

    private boolean isNeedAddRow ;
    private boolean isNeedDeleteRow ;
    private int index;

}
