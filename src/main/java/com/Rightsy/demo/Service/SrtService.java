package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateSrtReqDto;
import com.Rightsy.demo.Dto.SrtResponseDto;

import java.util.List;

public interface SrtService {

    ApiResponseDto createSrt(CreateSrtReqDto createSrtReqDto);
    ApiResponseDto deleteSrt(Long id);
    List<SrtResponseDto> getAllSrt();
    SrtResponseDto getSrtById(Long id);

}
