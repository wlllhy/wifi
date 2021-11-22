package edu.cs.scu.analysis.offlineanalysis

import edu.cs.scu.dao.impl.UserDaoImpl

class GetStayTime extends Runnable {
  override def run(): Unit = {
    while(true){
      val userDao: UserDaoImpl = new UserDaoImpl()
      userDao.updateStayTime()
      Thread.sleep(60000)
    }
  }
}
