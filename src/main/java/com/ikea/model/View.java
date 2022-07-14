package com.ikea.model;

public class View {

	public static interface viewProduct {

		public static interface viewProductIs {
		}

		public static interface viewProductAvailable extends viewProductIs {
		}

		public static interface viewProductByCategory extends viewProductIs {

		}

	}
}
