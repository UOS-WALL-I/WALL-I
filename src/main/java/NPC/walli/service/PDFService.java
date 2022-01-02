package NPC.walli.service;

import NPC.walli.domain.PDF;
import NPC.walli.repository.PDFRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PDFService {
    @Autowired
    private PDFRepository pdfRepository;

    public String addPDF(String title, MultipartFile file) throws IOException {
        PDF pdf = new PDF();
        pdf.setTitle(title);
        pdf.setFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        pdf = pdfRepository.insert(pdf);
        return pdf.getId();
    }

    public PDF getPDF(String id) {
        return pdfRepository.findById(id).get();
    }
}
