package com.Rightsy.demo.controller;

import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateSrtReqDto;
import com.Rightsy.demo.Service.SrtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;


    @RestController
    @RequiredArgsConstructor
    public class SrtController {

        private final SrtService srtService;

        private final ObjectMapper objectMapper;

        @PostMapping(
                value = "/admin/createSrt",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE
        )
        public ApiResponseDto createSrt(
                @RequestPart("srt") String srtJson,
                @RequestPart("images") List<MultipartFile> images
        ) throws IOException {

            CreateSrtReqDto createSrtReqDto =
                    objectMapper.readValue(srtJson, CreateSrtReqDto.class);

            return srtService.createSrt(createSrtReqDto, images);
        }
    }

