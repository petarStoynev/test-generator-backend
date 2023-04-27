package testgenerator.bg.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testgenerator.bg.entity.File;
import testgenerator.bg.services.FileService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/auth/files")
@CrossOrigin(value = {"*"},exposedHeaders = {"Content-Disposition"})
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/subject={name}/teacher={teacher}/school={school}")
    public ResponseEntity<byte[]> getSubjectFile(@PathVariable String name,@PathVariable String teacher,@PathVariable String school) throws DocumentException, FileNotFoundException {

        ByteArrayOutputStream byteArrayOutputStream = fileService.createFileDocument(name,teacher,school);

        byte[] pdfBytes =byteArrayOutputStream.toByteArray();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.setContentLength(pdfBytes.length);
        header.set("Content-Disposition","attachment; filename"+name+"-test");

        return new ResponseEntity<>(pdfBytes,header, HttpStatus.OK);

    }

}
