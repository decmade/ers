module.exports = {
	servletRoot: "/ers",

	AnchorWrapper : {
		anchorElement: null,

		setAnchor: function( element ) {
			this.anchorElement = element;
			return this;
		},
		getUrl: function() {
			return this.anchorElement.href;
		},
		getUri: function() {
			let url = this.getUrl();
			let delimiter = this.getUrlDelimiter();

			return url.substr( url.indexOf(delimiter) + delimiter.length );
		},
		getTargetedResource : function() {
			let uri = this.getUri();

			return uri.substr( uri.indexOf("/") );
		},
		getUrlDelimiter: function() {
			let url = this.getUrl();

			if ( url.startsWith("file") ) {
				return ":///:";
			} else {
				return "://";
			}
		},
		getServerRoot: function() {
			let resource = this.getTargetedResource();
			let href = this.anchorElement.href;

			return href.substring(0, (href.length - resource.length) );
		},
		injectResourcePrefix: function( prefix ) {
			let resource = this.getTargetedResource();
			let serverRoot = this.getServerRoot();

			this.anchorElement.href = [
				serverRoot,
				prefix,
				resource
			].join("");
		},
	},

	LoginForm : {
		elementId: "#user-landing",
		semaphore: 0,

		attachEvents: function() {
			$(this.elementId).find("input")
				.focus( () => {
					Common.LoginForm.semaphore++;
					Common.LoginForm.highlightItems() 
				})
				.focusout( () => {
					Common.LoginForm.semaphore--;
					Common.LoginForm.unHighlightItems() 
				});
		},
		highlightItems: function() {
			$(this.elementId).find(".fa-user-o").switchClass("fa-user-o", "fa-user", 0);
			$(this.elementId).find(".btn-outline-primary")
				.switchClass("btn-outline-primary", "btn-primary", 0)
				.removeClass("disabled");
		},
		unHighlightItems: function() {
			if ( this.semaphore == 0 ) {
				$(this.elementId).find(".fa-user").switchClass("fa-user", "fa-user-o", 0);
				$(this.elementId).find(".btn-primary")
				.switchClass("btn-primary", "btn-outline-primary", 0)
				.addClass("disabled");

			}
		},		
	},
};