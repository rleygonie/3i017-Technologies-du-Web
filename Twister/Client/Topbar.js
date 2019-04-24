import React, { Component } from 'react';
import './Topbar.css';
import logo from "./look.png";
import deco from "./deco.png"
class Topbar extends Component {
  constructor(props)
  {
  	super(props);
	  this.handleDeco = this.handleDeco.bind(this);
	  this.handleHome = this.handleHome.bind(this);
	  this.handleInputChange = this.handleInputChange.bind(this);
	  this.handleSearch = this.handleSearch.bind(this)
  }
  handleDeco(event) {
    this.props.signOut()
  }
  handleHome(event)
  {
	  this.props.goHome();
  }
  handleInputChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;

    this.setState({
      [name]: value
	});
	}
	handleSearch(event)
	{
		this.props.search(this.state.search);
	}
	render()
	{
	return(
		<div className="topbarConteneur">
			<div className="topbarConteneur2">
				<div className="topbarConteneur3">
					<div className="topbarConteneur4">
						<span className="home_notif">
							<img  id="home" src="https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png" alt="profil" onClick={this.handleHome}/> 
						</span>
						<img className="icone" src="https://www.brandcrowd.com/gallery/brands/pictures/picture12632215544704.jpg" alt="logo" onClick={this.handleHome}/>
						<div className="divsearch">
							<input className="inputSearch" type="text" name="search" onChange={this.handleInputChange}/>
							<img className="boutonSearch" src={logo} onClick={this.handleSearch}/>
						</div>
						<img id="deco" src={deco} alt="deconnexion" onClick={this.handleDeco}/>
						
					</div>
				</div>
			</div>

		</div>

		)
}

}
export default Topbar;