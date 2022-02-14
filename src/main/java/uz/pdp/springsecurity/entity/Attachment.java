package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileOriginalName;

    private long size;

    private String contentType;

    private String name;

    @ManyToOne
    private Branch branch;

    public Attachment(String fileOriginalName, long size, String contentType) {
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
    }
}
