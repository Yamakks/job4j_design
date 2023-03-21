package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void analysis(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("servertest.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.print("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    500 10:59:01
                    400 11:01:02
                    300 11:02:02""");
        }
        File target  = tempDir.resolve("targettest.csv").toFile();
        Analysis an = new Analysis();
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(rsl.toString());
    }
    }