package learningtest.org.apache.mina.example.sumup.codec;

import learningtest.org.apache.mina.example.sumup.message.AddMessage;
import learningtest.org.apache.mina.example.sumup.message.ResultMessage;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class SumUpProtocolCodecFactory extends DemuxingProtocolCodecFactory {

	public SumUpProtocolCodecFactory(boolean server) {
		if (server) {
			super.addMessageDecoder(AddMessageDecoder.class);
			super.addMessageEncoder(ResultMessage.class,
					ResultMessageEncoder.class);
		} else {
			super.addMessageEncoder(AddMessage.class, AddMessageEncoder.class);
			super.addMessageDecoder(ResultMessageDecoder.class);
		}
	}

}
