package cz.uhk.fim.pro2.game.listener;

import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;

/**
 * Created by AlesB on 06.11.16.
 */

public interface WorldListener {

  void catchHeart(Heart heart);

  void crashTube(Tube tube);

  void outOf();

}
