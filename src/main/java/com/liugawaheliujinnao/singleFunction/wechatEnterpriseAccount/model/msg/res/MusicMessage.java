package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/3.
 */
public class MusicMessage extends BaseMessage implements Serializable {

    /**音乐**/
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
