/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.model;

import com.health.contracts.entity.VisitationEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bobby
 */
@Getter
@Setter
public class VisitationLog {
    List<VisitationEntity> visitations;
    
}
