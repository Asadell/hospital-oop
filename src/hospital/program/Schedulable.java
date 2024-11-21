/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hospital.program;

import java.time.LocalDateTime;

/**
 *
 * @author LENOVO
 */
public interface Schedulable {
    LocalDateTime getSchedule();
    void setSchedule(LocalDateTime schedule);
}
