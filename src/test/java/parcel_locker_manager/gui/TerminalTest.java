package parcel_locker_manager.gui;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;


import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void when_getCommands_is_used_then_commands_should_be_displayed() {
        //given
        System.setOut(new PrintStream(outputStreamCaptor));
        Terminal terminal = new Terminal();
        //when
        terminal.getCommands();
        //then
        assertEquals("###########################\n" +
                "## PARCEL LOCKER MANAGER ##\n" +
                "###########################\n" +
                "     SELECT OPERATION:\n" +
                "\n[1]. ADD PARCEL LOCKER\n" +
                "[2]. REMOVE PARCEL LOCKER\n" +
                "[3]. DISPLAY ALL PARCEL LOCKERS\n" +
                "[4]. DISPLAY PARCEL LOCKER BY CITY NAME\n" +
                "[5]. UPDATE PARCEL LOCKER\n" +
                "[6]. ADD PARCEL\n" +
                "[7]. REMOVE PARCEL\n" +
                "[8]. DISPLAY PARCELS BY PARCEL LOCKER\n" +
                "[9]. UPDATE PARCEL\n" +
                "[10]. DISPLAY PARCELS BY SENDER\n" +
                "[11]. DISPLAY PARCELS BY RECIPIENT\n" +
                "[12]. EXIT PROGRAM", outputStreamCaptor.toString()
                .trim());
    }
}