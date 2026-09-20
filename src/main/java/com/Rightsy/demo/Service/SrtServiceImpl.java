package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateSrtReqDto;
import com.Rightsy.demo.Dto.SrtQuestionReqDto;
import com.Rightsy.demo.Dto.SrtResponseDto;
import com.Rightsy.demo.entity.Srt;
import com.Rightsy.demo.entity.SrtQuestion;
import com.Rightsy.demo.repository.SrtRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SrtServiceImpl implements SrtService {

    private final SrtRepo srtRepo;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponseDto createSrt(CreateSrtReqDto createSrtReqDto) {
        Srt srt=new Srt();
        srt.setTopic(createSrtReqDto.getTopic());

        for(SrtQuestionReqDto s:createSrtReqDto.getQuestions()){
            SrtQuestion question=new SrtQuestion();

            question.setQuestion(s.getQuestion());
            question.setPrerequisites(s.getPrerequisites());
            question.setAnswer(s.getAnswer());
            question.setNoteA(s.getNoteA());
            question.setNoteB(s.getNoteB());
            question.setNoteC(s.getNoteC());
            question.setNoteD(s.getNoteD());
            question.setOptionA(s.getOptionA());
            question.setOptionB(s.getOptionB());
            question.setOptionC(s.getOptionC());
            question.setOptionD(s.getOptionD());
            question.setImageUrl(s.getImageUrl());
            question.setSrt(srt);

            srt.getQuestions().add(question);
        }
        srtRepo.save(srt);


        return new ApiResponseDto("SRT Has been Added Successfully",true);
    }

    @Override
    public ApiResponseDto deleteSrt(Long id) {
        Srt srt=srtRepo.findById(id).orElseThrow(()->new EntityNotFoundException("SRT Not Found"));
        srtRepo.delete(srt);
        return new ApiResponseDto("SRT with id : " + id + "Has been Deleted Successfully",true);
    }

    @Override
    public List<SrtResponseDto> getAllSrt() {
        List<Srt> srtList=srtRepo.findAll();

        List<SrtResponseDto> srtResponseDtoList=srtList.stream().map(m->modelMapper.map(m,SrtResponseDto.class)).toList();

        return srtResponseDtoList;
    }

    @Override
    public SrtResponseDto getSrtById(Long id) {

        Srt srt=srtRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Srt Not Found"));

        return modelMapper.map(srt,SrtResponseDto.class);
    }
}


