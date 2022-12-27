package com.mygrammar.manager.share.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameValueList {

    private List<NameValue> nameValueList = new ArrayList<>();

    public void addNameValue(NameValue nameValue) {
        nameValueList.add(nameValue);
    }
}
