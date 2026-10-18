package app.assignments;

import app.assignments.message.CustomMessage;
import app.assignments.message.PingMessage;
import app.assignments.writer.ListWriter;
import lombok.var;
import org.junit.jupiter.api.Test;

public class MainTests {
    @Test
    void testPingMessage() {
        var pingMessage = new PingMessage();
        assert pingMessage.getSender().equals("ping");
        assert pingMessage.getRecipient().equals("pong");
        assert pingMessage.getText().equals("Ping has empty body");
    }

    @Test
    void testCustomMessage() {
        var customMessage = new CustomMessage("sender", "recipient", "text");
        assert customMessage.getSender().equals("sender");
        assert customMessage.getRecipient().equals("recipient");
        assert customMessage.getText().equals("text");
    }

    @Test
    void testReplyMessage() {
        var replyMessage = new CustomMessage("pong", "ping", "Pong has empty body");
        assert replyMessage.getSender().equals("pong");
        assert replyMessage.getRecipient().equals("ping");
        assert replyMessage.getText().equals("Pong has empty body");

        var emptyMessage = new CustomMessage();
        assert emptyMessage.getSender() == null;
        assert emptyMessage.getRecipient() == null;
        assert emptyMessage.getText() == null;
    }

    @Test
    void testListWriter() {
        var listWriter = new ListWriter();
        var pingMessage = new PingMessage();
        listWriter.write(pingMessage);
        assert listWriter.listWrittenMessages().size() == 1;
        assert listWriter.listWrittenMessages().get(0).equals(pingMessage);
    }
}
