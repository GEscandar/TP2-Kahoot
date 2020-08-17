package edu.fiuba.algo3.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionGroup extends GameOption{
	
	List<GameOption> options;
	
	public OptionGroup(String text) {
		super(text);
	}

	public List<GameOption> getOptions() {
		return options;
	}

	public void setOptions(List<GameOption> options) {
		this.options = options;
	}
	
	public void addOptions(GameOption ... option){
		options.addAll(Arrays.asList(option));
	}

	public void addOption(GameOption option){
		options.add(option);
	}

	public void removeOptions(GameOption ... option){
		options.removeAll(Arrays.asList(option));
	}

	public void removeOption(GameOption option){
		options.remove(option);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OptionGroup other = (OptionGroup) obj;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}
	
	
	

}
