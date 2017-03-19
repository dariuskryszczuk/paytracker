package eu.greyson.parser;

import eu.greyson.payment.Payable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PaymentFileParser implements Parser<List<Payable>> {

    private final Parser<Payable> payableParser = new PaymentParser();

    /**
     * This parser is used for txt file containing payments in each of its line:
     * USD 1000
     * HKD 100
     * USD -100
     * It is loaded with {@code StarterFileChooser}.
     *
     * @param path to the file with 'starter' data.
     * @return
     * @throws ParserException
     */
    @NotNull
    @Override
    public List<Payable> parse(String path) throws ParserException {
        List<Payable> payments = new ArrayList<>();
        try (Stream<String> stream = this.getLines(path)) {
            stream.forEach(row -> payments.add(payableParser.parse(row)));
        }
        return payments;
    }

    @NotNull
    private Stream<String> getLines(String path) throws ParserException {
        try {
            Path p = Paths.get(path);
            return Files.lines(p);
        } catch (IOException e) {
            throw new ParserException("Can't parse content of file " + path);
        }
    }
}
