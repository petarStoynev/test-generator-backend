package testgenerator.bg.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import testgenerator.bg.entity.dto.QuestionDTO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


@Service
public class FileService {

    private final QuestionService questionService;

    public FileService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ByteArrayOutputStream createFileDocument(String name,String teacher,String school) throws FileNotFoundException, DocumentException {

        List<QuestionDTO> questionDTOS = questionService.getQuestionBySubjectName(name);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document,byteArrayOutputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER,16, BaseColor.BLACK);

        int counter = 1;

        StringBuilder info = new StringBuilder();
        info.append(String.format("Teacher: %s",teacher))
                .append(System.lineSeparator())
                .append(String.format("School: %s",school))
                .append(System.lineSeparator())
                .append(String.format("Subject: %s",name));

        Chunk infoChunk = new Chunk(String.valueOf(info),font);
        document.add(new Paragraph("\n"));
        document.add(infoChunk);

        for (QuestionDTO questionDTO : questionDTOS) {

            document.add(new Paragraph("\n"));

            StringBuilder builder = new StringBuilder(counter+". "+questionDTO.getDescription());
            builder.append(System.lineSeparator()).append("a. "+questionDTO.getA())
                    .append(System.lineSeparator()).append("b. "+questionDTO.getB())
                    .append(System.lineSeparator()).append("c. "+questionDTO.getC())
                    .append(System.lineSeparator());

            Chunk chunk = new Chunk(String.valueOf(builder),font);

            document.add(chunk);

            counter++;
            document.add(new Paragraph("\n"));

        }

        document.close();

        return byteArrayOutputStream;


    }


}
