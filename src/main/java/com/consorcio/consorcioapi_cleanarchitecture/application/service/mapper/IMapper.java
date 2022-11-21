package com.consorcio.consorcioapi_cleanarchitecture.application.service.mapper;

import org.springframework.stereotype.Service;

@Service
public interface IMapper<M,D> {
    M toModel(D dto);
    D toDTO(M model);
}
