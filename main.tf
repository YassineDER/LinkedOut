provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_cloud_run_service" "spring_app" {
  name     = "spring-backend"
  location = var.region
  template {
    spec {
      containers {
        image = var.image_url
        env {
          name  = "DB_PASS"
          value = var.env_var_value
        }
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }
}

resource "google_project_iam_member" "run_invoker" {
  project = var.project_id
  role    = "roles/run.invoker"
  member  = "serviceAccount:${google_cloud_run_service.spring_app.status[0].service_account}"
}

output "cloud_run_url" {
  value = google_cloud_run_service.spring_app.status[0].url
}
