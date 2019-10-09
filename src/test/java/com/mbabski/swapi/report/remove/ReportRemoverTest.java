package com.mbabski.swapi.report.remove;

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
public class ReportRemoverTest {

    private static final String CHARACTER_PHRASE1 = "R2D2";
    private static final String PLANET_NAME1 = "Mars";

    private static final String CHARACTER_PHRASE2 = "D303";
    private static final String PLANET_NAME2 = "Wenus";

    @Autowired
    private ReportRepository reportRepository;

    @Test
    @DirtiesContext
    public void shouldDeleteOneReport() {
        //given
        Report report1 = reportRepository.create(report1());
        Report report2 = reportRepository.create(report2());

        //when
        reportRepository.delete(report1.getReportId());

        //then
        Assertions.assertThat(reportRepository.readAll()).hasSize(1);
        Assertions.assertThat(reportRepository.readAll().get(0).getCharacterName()).isEqualTo(report2.getCharacterName());
        Assertions.assertThat(reportRepository.readAll().get(0).getPlanetName()).isEqualTo(report2.getPlanetName());
    }

    @Test
    public void shouldDeleteAllReports() {
        //given
        Report report1 = reportRepository.create(report1());
        Report report2 = reportRepository.create(report2());

        //when
        reportRepository.deleteAll();

        //then
        Assertions.assertThat(reportRepository.readAll()).hasSize(0);
    }

    private Report report1(){
        return Report.builder().filmName("Rocky").planetName(PLANET_NAME1).characterName(CHARACTER_PHRASE1).build();
    }

    private Report report2(){
        return Report.builder().filmName("Rambo").planetName(PLANET_NAME2).characterName(CHARACTER_PHRASE2).build();
    }

}