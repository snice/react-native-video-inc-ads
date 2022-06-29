package com.brentvatne.exoplayer;

import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class BlackListMediaCodecSelector implements MediaCodecSelector {
    @Override
    public List<MediaCodecInfo> getDecoderInfos(String mimeType, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws MediaCodecUtil.DecoderQueryException {

        List<MediaCodecInfo> codecInfos = MediaCodecUtil.getDecoderInfos(
                mimeType, requiresSecureDecoder, requiresTunnelingDecoder);

        // remove failing "OMX.MTK.VIDEO.DECODER.AVC.secure"
        List<MediaCodecInfo> filteredCodecInfos = new ArrayList<>();
        for (MediaCodecInfo codecInfo: codecInfos) {
            if (!"OMX.MTK.VIDEO.DECODER.AVC.secure".equals(codecInfo.name)) {
                filteredCodecInfos.add(codecInfo);
            }
        }
        return filteredCodecInfos;
    }

}