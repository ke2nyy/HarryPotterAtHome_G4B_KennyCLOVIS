package com.isep.hpah.core.enemies;

import com.isep.hpah.core.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEnemy extends Character {

    private String name;
    private EnemyType enemyType;
}
