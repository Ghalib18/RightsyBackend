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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SrtServiceImpl implements SrtService {

    private final SrtRepo srtRepo;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;


    @Override
    @Transactional
    public ApiResponseDto createSrt(CreateSrtReqDto createSrtReqDto, List<MultipartFile> images)  {
        Srt srt=new Srt();
        srt.setTopic(createSrtReqDto.getTopic());

        for (int i = 0; i < createSrtReqDto.getQuestions().size(); i++) {

            SrtQuestionReqDto s = createSrtReqDto.getQuestions().get(i);

            SrtQuestion question = new SrtQuestion();

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

            // Upload corresponding image
            String imageUrl = cloudinaryService.uploadImage(images.get(i));

            question.setImageUrl(imageUrl);

            // Connect question to SRT
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


