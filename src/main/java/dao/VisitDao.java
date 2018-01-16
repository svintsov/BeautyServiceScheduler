package dao;

import entity.Visit;
import java.util.List;

public interface VisitDao extends Dao<Visit,Integer> {
  List<Visit> findAll();
}
