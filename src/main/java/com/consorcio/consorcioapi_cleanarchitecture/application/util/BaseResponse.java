package com.consorcio.consorcioapi_cleanarchitecture.application.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T>{

    public Boolean IsSucces;
    public T DataResponse;
    public String Message ;
}
