package uz.pdp.springsecurity;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.SneakyThrows;

import java.text.ParseException;

public class Test {
    @SneakyThrows
    public static void main(String[] args) throws ParseException {
        PdfWriter writer = new PdfWriter("src/main/resources/pdfFiles/test.pdf");
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph("Branch");
        paragraph.setFontSize(40);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        Paragraph paragraph1 = new Paragraph("Toshkent, Shayxontoxur, qoratosh, 35");
        paragraph1.setFontSize(12);
        paragraph1.setMarginTop(-12);
        paragraph1.setMarginBottom(-7);
        paragraph1.setTextAlignment(TextAlignment.CENTER);


        Text text = new Text("Invoice");
        text.setFontSize(24);

        Paragraph paragraph2 = new Paragraph(text);
        paragraph2.setTextAlignment(TextAlignment.CENTER);

        Text id = new Text("id");
        Text text1 = new Text("Invoice N: ");
        text1.setBold();
        text1.setFontSize(14);
        Paragraph paragraph3 = new Paragraph();
        paragraph3.add(text1);
        paragraph3.add(id);


        Paragraph date = new Paragraph();
        Text dateTime = new Text("2020/01/01");
        Text dateText = new Text("Date: ");
        dateText.setBold();
        dateText.setFontSize(14);
        date.add(dateText);
        date.add(dateTime);

        Paragraph customer = new Paragraph();
        Text custValue = new Text("Supplier");
        Text custText = new Text("Customer: ");
        custText.setFontSize(14);
        custText.setBold();
        customer.add(custText);
        customer.add(custValue);

        Paragraph seller = new Paragraph();
        Text sellerText = new Text("Seller: ");
        Text sellerValue = new Text("Sardorbek");
        sellerText.setFontSize(14);
        sellerText.setBold();
        seller.add(sellerText);
        seller.add(sellerValue);

        Table header = new Table(2);
        Cell row1 = new Cell();
        row1.add(paragraph3);
        row1.add(customer);
        Cell row2 = new Cell();
        row2.add(date);
        row2.add(seller);
        row1.setBorder(Border.NO_BORDER);
        row2.setBorder(Border.NO_BORDER);
        row2.setTextAlignment(TextAlignment.RIGHT);
        row1.setWidth(new UnitValue(UnitValue.PERCENT , 50));
        row2.setWidth(new UnitValue(UnitValue.PERCENT , 50));
        header.setWidth(new UnitValue(UnitValue.PERCENT , 100));
        header.addCell(row1);
        header.addCell(row2);

        Table table = new Table(2);
        Text productText = new Text("Product: ");
        productText.setBold();
        Text quantityText = new Text("Quantity");
        quantityText.setBold();
        Text unitText = new Text("Unit Price");
        unitText.setBold();
        Text totalText = new Text("Total Sum");
        totalText.setBold();
        custText.setFontSize(14);
        custText.setBold();
        Cell col1 = new Cell();
        Cell col2 = new Cell();
        col1.add(new Paragraph(productText));

        Table columns = new Table(3);
        Cell quantity = new Cell();
        quantity.add(new Paragraph(quantityText));
        Cell unitPrice = new Cell();
        unitPrice.add(new Paragraph(unitText));
        Cell totalSum = new Cell();
        totalSum.add(new Paragraph(totalText));
        columns.addCell(quantity);
        columns.addCell(unitPrice);
        columns.addCell(totalSum);
        col2.add(columns);

        col1.setBorder(Border.NO_BORDER);
        col2.setBorder(Border.NO_BORDER);
        quantity.setBorder(Border.NO_BORDER);
        unitPrice.setBorder(Border.NO_BORDER);
        totalSum.setBorder(Border.NO_BORDER);
        table.setBorderTop(new SolidBorder(1));
        quantity.setBorderRight(new SolidBorder(1));
        quantity.setBorderLeft(new SolidBorder(1));
        unitPrice.setBorderRight(new SolidBorder(1));


        table.addCell(col1);
        table.addCell(col2);

        Table total = new Table(2);
        Text subtotalText = new Text("Subtotal: ");
        subtotalText.setBold();
        subtotalText.setFontSize(14);
        Text totalsum = new Text("Total: ");
        totalsum.setFontSize(14);
        totalsum.setBold();
        Cell sub = new Cell();
        sub.add(new Paragraph(subtotalText));
        sub.add(new Paragraph(totalsum));

        Cell value = new Cell();
        Text subtotalValue = new Text("0");
        subtotalValue.setFontSize(14);
        Text totalsumValue = new Text("0");
        totalsumValue.setFontSize(14);
        value.add(new Paragraph(subtotalValue));
        value.add(new Paragraph(totalsumValue));

        total.addCell(sub);
        total.addCell(value);

        total.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        value.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        sub.setWidth(new UnitValue(UnitValue.PERCENT, 50));

        value.setTextAlignment(TextAlignment.RIGHT);
        sub.setTextAlignment(TextAlignment.LEFT);

        total.setMarginTop(50);
        sub.setBorder(Border.NO_BORDER);
        value.setBorder(Border.NO_BORDER);
        total.setBorderTop(new SolidBorder(1));

        table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        col1.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        col2.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        columns.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        columns.setTextAlignment(TextAlignment.CENTER);
        table.setMarginTop(30);

        Paragraph right = new Paragraph();
        right.setTextAlignment(TextAlignment.RIGHT);
        right.add(total);


        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(header);
        document.add(table);
        document.add(right);

        document.close();
    }
}
