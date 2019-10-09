package com.mbabski.swapi.report.find;

import com.mbabski.swapi.domain.Report;
import com.mbabski.swapi.repository.ReportRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportReaderTest {

    private static final String CHARACTER_PHRASE1 = "R2D2";
    private static final String PLANET_NAME1 = "Mars";

    private static final String CHARACTER_PHRASE2 = "D303";
    private static final String PLANET_NAME2 = "Wenus";

    @Autowired
    private ReportRepository reportRepository;

    @Test
    @DirtiesContext
    public void shouldReadReportById() {
        //given
        //when
        Report report1 = reportRepository.create(report1());

        //then
        Assertions.assertThat(reportRepository.read(report1.getReportId())).isPresent();
        Assertions.assertThat(reportRepository.read(report1.getReportId()).get().getCharacterName())
                .isEqualTo(report1.getCharacterName());
        Assertions.assertThat(reportRepository.read(report1.getReportId()).get().getPlanetName())
                .isEqualTo(report1.getPlanetName());
    }

    @Test
    @DirtiesContext
    public void shouldNotReadReportById() {
        //given
        //when
        reportRepository.read(4L);
        //then
        Assertions.assertThat(reportRepository.read(4L)).isEmpty();
    }

    @Test
    @DirtiesContext
    public void shouldReadAllReports() {
        //given
        //given
        Report report1 = reportRepository.create(report1());
        Report report2 = reportRepository.create(report2());

        //then
        Assertions.assertThat(reportRepository.readAll()).hasSize(2);
        Assertions.assertThat(reportRepository.readAll().get(0).getPlanetName()).isEqualTo(report1.getPlanetName());
        Assertions.assertThat(reportRepository.readAll().get(1).getPlanetName()).isEqualTo(report2.getPlanetName());
    }

    private Report report1() {
        return Report.builder().filmName("Rocky").planetName(PLANET_NAME1).characterName(CHARACTER_PHRASE1).build();
    }

    private Report report2() {
        return Report.builder().filmName("Rambo").planetName(PLANET_NAME2).characterName(CHARACTER_PHRASE2).build();
    }
}