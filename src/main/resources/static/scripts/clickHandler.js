AFRAME.registerComponent("clickhandler", {
		init: function() {	
			this.el.addEventListener("click", function(evt) {
				let icon = evt.target || evt.srcElement;
				let index = parseInt(icon.getAttribute("id"));
				let panel = parent.document.getElementById("info-panel");
				let el = parent.db[index];
				panel.innerHTML = "";
				let backBtn = parent.document.createElement("button");
				backBtn.setAttribute("class", "button close");
				backBtn.setAttribute("onClick", "btnClose()");
				panel.appendChild(backBtn);
				let image = parent.document.createElement("img");
				image.setAttribute("src", "/images/" + el.image);
				panel.appendChild(image);
				let name = parent.document.createElement("h1");
				name.innerHTML = el.name;
				panel.appendChild(name);
				for(let i = 0; i < el.infos.length; i++){
					let title = parent.document.createElement("h2");
					title.innerHTML = el.infos[i].title;
					let content = parent.document.createElement("div");
					content.innerHTML = el.infos[i].content;
					panel.appendChild(title);
					panel.appendChild(content);
				}
				parent.displayInfoPanel();
			});
		}
});